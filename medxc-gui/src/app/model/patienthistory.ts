import { Record } from './record';
import { Test } from './test';

export class PatientHistory {
    records: Record[];
    tests: Test[];
    constructor(
        records: Record[],
        tests: Test[],
    ) {
        this.records = records;
        this.tests = tests;
    }
}
