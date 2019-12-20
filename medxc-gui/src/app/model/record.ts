export class Record {
    anamnesis: string;
    observation: string;
    therapy: string;
    constructor(
        anamnesis: string,
        observation: string,
        therapy: string
    ) {
        this.anamnesis = anamnesis;
        this.observation = observation;
        this.therapy = therapy;
    }
}
