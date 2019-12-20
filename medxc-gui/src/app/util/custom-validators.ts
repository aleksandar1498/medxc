import { ValidatorFn, ValidationErrors, FormGroup } from '@angular/forms';

export const passwordMatchValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
    const pass = control.get('password');
    const confirmPass = control.get('confirmPassword');
    const result = pass && confirmPass &&
        pass.value !== '' && confirmPass.value !== '' &&
        pass.value !== confirmPass.value ? { passwordsDontMatch: true } : null;
    if (result !== null) {
        control.get('confirmPassword').setErrors(result);
    }
    return result;
};
