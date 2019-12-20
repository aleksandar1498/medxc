import { Doctor } from './doctor';
import { Patient } from './patient';
import { Record } from './record';

export class Appointment {
    constructor(date: Date, status: string, specialty: string, record: Record, doctor: Doctor, patient: Patient) {
        this.date = date;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
        this.record = record;
        this.specialty = specialty;
    }
    date: Date;
    status: string;
    doctor: Doctor;
    patient: Patient;
    record: Record;
    specialty: string;

}
