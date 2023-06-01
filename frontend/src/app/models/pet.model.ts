export interface Mascota {
  id: string;
  nombre: string;
  fotos: string[];
  descripcion: string;
  cuidados: string;
  localidad: string;
  contacto: number;
  fechaDeNacimiento: string;
  esterilizado: boolean;
  desparacitado: boolean;
  vacunado: boolean;
  nivelActividad: string;
  size: null;
  especie: string;
  sex: string;
  activo: boolean;
}

export interface Category {
  id: string;
  name: string;
}

export interface CreateMascota extends Omit<Mascota, 'id' | 'category' | 'activo'> {
}

export interface UpdateMascota extends Partial<CreateMascota> {}
