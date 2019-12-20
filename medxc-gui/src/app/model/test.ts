export class Test {
    testType: string;
    dueDate: Date;
    value: number;
    dateTaken: Date;
    constructor(
        testType: string,
        dueDate: Date,
        value: number,
        dateTaken: Date

    ) {
        this.testType = testType;
        this.dueDate = dueDate;
        this.value = value;
        this.dateTaken = dateTaken;
    }
}
