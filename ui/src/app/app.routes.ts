import { Routes } from '@angular/router';
import { CreateQuizComponent } from '../component/create-quiz/create-quiz.component';
import { QuizComponent } from '../component/quiz/quiz.component';

export const routes: Routes = [
    { path: '', component: CreateQuizComponent },
    { path: 'quiz', component: QuizComponent },
    { path: '**', redirectTo: '' }
];
