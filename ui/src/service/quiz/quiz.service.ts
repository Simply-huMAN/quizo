import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private apiUrl = 'http://localhost:8080/chat';

  constructor(private http: HttpClient) { }

  createQuiz(topic: string) {
    console.log(`Creating quiz for topic: ${topic}`);

    return this.http.post(`${this.apiUrl}/create-quiz`, { "content": topic });
  }
}
