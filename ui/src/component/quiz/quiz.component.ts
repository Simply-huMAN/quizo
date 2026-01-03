import { Component, OnInit } from '@angular/core';
import { QuizSchema } from '../../dto/QuizSchema';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatRadioModule } from '@angular/material/radio';

@Component({
  selector: 'app-quiz',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatRadioModule],
  templateUrl: './quiz.component.html',
  styleUrl: './quiz.component.css'
})
export class QuizComponent implements OnInit {
  quizData: QuizSchema | null = null;
  userAnswers: number[] = [];

  ngOnInit() {
      this.quizData = JSON.parse(localStorage.getItem('quizData') || '{}');
      console.log('Loaded quiz data:', this.quizData);
  }
}
