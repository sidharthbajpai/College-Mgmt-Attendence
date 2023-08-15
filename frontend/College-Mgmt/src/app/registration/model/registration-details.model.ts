export class RegistrationDetails {
    constructor(
        public firstName: string,
        public lastName: string,
        public username: string,
        public password: string,
        public phoneNumber: string,
        public location: string,
        public email: string,
        public roleId: number,
        public departmentId?: number
        ) {}
}
