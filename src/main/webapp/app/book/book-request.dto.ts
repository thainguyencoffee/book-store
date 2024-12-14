export class BookRequestDto {
  constructor(data: Partial<BookRequestDto>) {
    Object.assign(this, data);
  }

  isbn?: string | null;
}
