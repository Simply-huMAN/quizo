export class Question {
    question: string;
    options: string[];
    answer: number;
    explanation: string;

    constructor(question: string, options: string[], answer: number, explanation: string) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.explanation = explanation;
    }
}