import { Question } from './Question';

export class QuizSchema {
    title: string;
    questions: Question[];
    tags: string[];

    constructor(title: string, questions: Question[], tags: string[]) {
        this.title = title;
        this.questions = questions;
        this.tags = tags;
    }
}