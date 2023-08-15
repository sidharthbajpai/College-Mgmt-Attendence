export class Request {
    constructor(
        public requestId: number,
        public firstName: string,
        public lastName: string,
        public username: string,
        public phoneNumber: string,
        public location: string,
        public email: string,
        public status: string,
        public departmentId: string,
        public departmentName: string,
        public roleId: string,
        public roleName: string) { }
}