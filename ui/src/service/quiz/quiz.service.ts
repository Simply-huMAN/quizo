import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Answer } from '../../dto/Answer';
import { Submission } from '../../dto/Submission';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  createQuiz(topic: string) {
    console.log(`Creating quiz for topic: ${topic}`);

    return this.http.post(`${this.apiUrl}/chat/create-form`, { "content": topic });
  }

  submitQuiz(submission: Submission) {
    console.log('Submitting quiz answers:', submission);

    return this.http.post(`${this.apiUrl}/form/submit`, submission);
  }

  getResults(submissionId: string) {
    console.log(`Fetching results for submission ID: ${submissionId}`);

    return this.http.get(`${this.apiUrl}/form/result?submissionId=${submissionId}`);
  }
}
