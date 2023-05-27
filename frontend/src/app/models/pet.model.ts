export interface Mascota {
  id: string;
  nombre: string;
  price: number;
  images: string [];
  description: string;
  category: Category;
}

export interface Category{
  id: string;
  name: string;
}

export interface CreateMascota extends Omit<Mascota, 'id' | 'category'> {
  categoryId: number;
}

export interface UpdateMascota extends Partial<CreateMascota> {}
