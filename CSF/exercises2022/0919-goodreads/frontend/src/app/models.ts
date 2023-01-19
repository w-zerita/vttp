export interface BookSummary {
    bookId: string
    title: string
}

export interface BookDetails {
    bookId: string
    title: string
    authors: string
    description: string
    rating: number
    genres: string
    imageUrl: string
}