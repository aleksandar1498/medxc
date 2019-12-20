import { Appointment } from './appointment';

export class Patient {
    id: string;
    name: string;
    email: string;
    sex: string;
    phoneNumber: string;
    address: string;
    appointments: Appointment[];
    constructor(
        id: string,
        name: string,
        email: string,
        sex: string,
        phoneNumber: string,
        address: string,
        appointments: Appointment[],
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.appointments = appointments;
    }
}

