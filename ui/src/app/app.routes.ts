import { Routes } from '@angular/router';
import { CreateQuizComponent } from '../component/create-quiz/create-quiz.component';
import { QuizComponent } from '../component/quiz/quiz.component';
import { ResultComponent } from '../component/result/result.component';
import { NoDataComponent } from '../component/no-data/no-data.component';

export const routes: Routes = [
    { path: '', component: CreateQuizComponent },
    { path: 'quiz', component: QuizComponent },
    { path: 'results', component: ResultComponent },
    { path: 'no-data', component: NoDataComponent },
    { path: '**', redirectTo: '' }
];
