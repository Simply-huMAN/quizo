import { Component, OnInit } from '@angular/core';
import { QuizSchema } from '../../dto/QuizSchema';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from "@angular/material/button";
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Answer } from '../../dto/Answer';
import { QuizService } from '../../service/quiz/quiz.service';

@Component({
  selector: 'app-quiz',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatRadioModule, MatButtonModule, FormsModule],
  templateUrl: './quiz.component.html',
  styleUrl: './quiz.component.css'
})
export class QuizComponent implements OnInit {
  quizData: QuizSchema | null = null;
  userAnswers: Answer[] = [];
  showResults: boolean = false;

  constructor(private quizService: QuizService, private router: Router) {
      this.quizService = quizService;
  }

  ngOnInit() {
      this.quizData = JSON.parse(localStorage.getItem('quizData') || '{}');
      this.userAnswers = new Array(this.quizData?.questions.length || 0).fill(-1);
      console.log('Loaded quiz data:', this.quizData);
  }

  handleSubmit() {
    if(!this.quizData) return;
    console.log('userAnswers: ', this.userAnswers.length);
    console.log('questions: ', this.quizData.questions.length);
    if(this.userAnswers.length !== this.quizData.questions.length) {
        alert('Please answer all questions before submitting.');
        return;
    }
    console.log('Answers: ', this.userAnswers);
    let score = 0;

    this.quizService.submitQuiz({
      formId: this.quizData.formId,
      userId: 'e4535602-01b5-4064-9a51-7555310d89dc',
      answers: this.userAnswers
    }).subscribe(response => {
      console.log('Quiz submitted successfully:', response);
    }, error => {
      console.error('Error submitting quiz:', error);
    })

    // this.quizData.questions.forEach((question, index) => {
    //   if(question.answer == this.userAnswers[index]) score++;
    // })
    // localStorage.setItem('userAnswers', JSON.stringify(this.userAnswers));

    document.getElementsByTagName('button')[0].hidden = true;
    this.router.navigate(['/results']);
  }
}
