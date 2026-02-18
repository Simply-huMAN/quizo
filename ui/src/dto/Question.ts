export class Question {
    questionId: string;
    text: string;
    isRequired: boolean;
    options: Options[];

    constructor(id: string, question: string, options: Options[], isRequired: boolean) {
        this.questionId = id;
        this.text = question;
        this.options = options;
        this.isRequired = isRequired;
    }
}

export class Options {
    id: string;
    value: string;

    constructor(id: string, value: string) {
        this.id = id;
        this.value = value;
    }
}