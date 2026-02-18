export class Answer {
    questionId: string;
    selectedOptionId: string[];

    constructor(question: string, selectedOptionId: string[]) {
        this.questionId = question;
        this.selectedOptionId = selectedOptionId;
    }
}