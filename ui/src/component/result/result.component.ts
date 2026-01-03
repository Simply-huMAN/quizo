import { Component, OnInit } from '@angular/core';
import { QuizSchema } from '../../dto/QuizSchema';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from "@angular/material/button";
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-result',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatRadioModule, MatButtonModule, FormsModule],
  templateUrl: './result.component.html',
  styleUrl: './result.component.css'
})
export class ResultComponent implements OnInit {
  quizData: QuizSchema | null = null;
  userAnswers: number[] = [];
  showResults: boolean = false;
  score: number = 0;

  constructor(private router: Router) {}
  
  ngOnInit() {
    this.quizData = JSON.parse(localStorage.getItem('quizData') || '{}');
    this.userAnswers = JSON.parse(localStorage.getItem('userAnswers') || '[]');

    if(!this.quizData || this.userAnswers.length === 0){ 
      this.router.navigate(['/no-data']);
      return;
    }
    console.log('Loaded results:', this.quizData);

    this.quizData?.questions.forEach((question, index) => {
      if(question.answer == this.userAnswers[index]) this.score++;
    })
  }
  
  handleReviewAnswers() {
    document.getElementsByTagName('button')[0].hidden = true;
    this.showResults = true;
    localStorage.removeItem('quizData');
    localStorage.removeItem('userAnswers');
  }
}
