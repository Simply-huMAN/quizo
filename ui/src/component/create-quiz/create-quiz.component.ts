import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { QuizService } from '../../service/quiz/quiz.service';
import { QuizSchema } from '../../dto/QuizSchema';
import { Router } from '@angular/router';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

interface QuizResponse {
  body: {
    choices: Array<{
      message: {
        content: string;
      };
    }>;
    id: string;
  }
}

@Component({
  selector: 'app-create-quiz',
  standalone: true,
  imports: [CommonModule, MatInputModule, MatButtonModule, FormsModule, MatProgressSpinnerModule],
  templateUrl: './create-quiz.component.html',
  styleUrl: './create-quiz.component.css'
})
export class CreateQuizComponent {
  topic = signal('');
  isLoading = signal(false);

  constructor(private quizService: QuizService, private router: Router) {
    this.quizService = quizService;
  }

  handleSubmit() {
    if(!this.topic().trim()) return;
    console.log('Generating quiz for topic:', this.topic());
    this.isLoading.set(true);

    this.quizService.createQuiz(this.topic()).subscribe({
      next: (response) => {
        console.log('Quiz created successfully:', response);
        console.log(response);

        const data = JSON.parse((response as QuizResponse).body.choices[0].message.content);
        localStorage.setItem('quizData', JSON.stringify(data));
        this.router.navigate(['/quiz']);
        this.isLoading.set(false);
      },
      error: (error) => {
        this.isLoading.set(false);
        console.error('Error creating quiz:', error);
      }
    });
  }

}
