import { Question } from './Question';

export class QuizSchema {
    formId: string;
    title: string;
    questions: Question[];
    tags: string[];

    constructor(formId: string, title: string, questions: Question[], tags: string[]) {
        this.formId = formId;
        this.title = title;
        this.questions = questions;
        this.tags = tags;
    }
}