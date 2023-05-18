export interface Mascota {
  id: string;
  title: string;
  price: number;
  images: string [];
  description: string;
  category: Category;
}

export interface Category{
  id: string;
  name: string;
}
