import {User} from "./user";

export class Rent {
  id: number;
  date: string;
  timeOfStart: string;
  timeOfEnd: string;
  price: number;
  comment: string;
  status: string;
  user: User;
}
