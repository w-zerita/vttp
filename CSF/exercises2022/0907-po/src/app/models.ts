export interface Order {
    orderId?: string
    name: string
    mobile: string
    lineItems: LineItem[]
}

export interface LineItem {
    item: string
    quantity: number
}

export type OrderDB = {
    [ key: string ]: Order
}