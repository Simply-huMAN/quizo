import { Answer } from "./Answer";

export class Submission {
    formId: string;
    userId: string;
    answers: Answer[];

    constructor(formId: string, userId: string, answers: Answer[]) {
        this.formId = formId;
        this.userId = userId;
        this.answers = answers;
    }
}