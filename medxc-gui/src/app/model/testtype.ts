export class TestType {
    testTypeName: string;
    refValues: string;
    constructor(
        name: string,
        refValues: string
    ) {
        this.testTypeName = name;
        this.refValues = refValues;
    }
}