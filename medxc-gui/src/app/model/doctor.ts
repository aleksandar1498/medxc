import { Specialty } from './specialty';
import { Appointment } from './appointment';

export class Doctor {
    id: string;
    name: string;
    email: string;
    phoneNumber: string;
    active: boolean;
    specialties: Specialty[];
    appointments: Appointment[];

    constructor(id: string, name: string, email: string, phoneNumber: string, active: boolean,
                specialties: Specialty[], appointments: Appointment[]) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.active = true;
        this.specialties = specialties;
        this.appointments = appointments;
    }
    getSpecialties() {
        return this.specialties;
    }
}
