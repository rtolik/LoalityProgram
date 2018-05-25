import {User} from "./user";

export class Rent {
  id: number;
  date: string;
  duration: number;
  timeOfStart: string;
  timeOfEnd: string;
  price: number;
  comment: string;
  rentStatus: string;
  user: User;
}
