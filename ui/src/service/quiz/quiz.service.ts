import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Answer } from '../../dto/Answer';
import { Submission } from '../../dto/Submission';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private apiUrl = 'http://localhost:8080/chat';

  constructor(private http: HttpClient) { }

  createQuiz(topic: string) {
    console.log(`Creating quiz for topic: ${topic}`);

    return this.http.post(`${this.apiUrl}/create-form`, { "content": topic });
  }

  submitQuiz(submission: Submission) {
    console.log('Submitting quiz answers:', submission);

    return this.http.post(`${this.apiUrl}/submit-form`, submission);
  }
}
