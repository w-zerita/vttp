export interface Contact {
    name: string
    email: string
    mobile: string
}

export interface Response {
    code: number
    message?: string
    data?: Contact
}