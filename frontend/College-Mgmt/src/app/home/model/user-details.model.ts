export class UserDetails {
    constructor(
        public firstName: string,
        public lastName: string,
        public username: string,
        public phoneNumber: string,
        public location: string,
        public email: string,
        public departmentName?: string,
        public studentId?: number,
        public teacherId?: number,
        public adminId?: number
    ) { }
}








