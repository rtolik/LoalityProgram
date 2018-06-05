export  const timeMask = [/[0-9]/,/\d/,':',/[0-9]/,/\d/];
export  const phoneMask = ['3', '8', ' ', '(', /[0-9]/, /\d/, /\d/, ')', '-', /\d/, /\d/, '-', /\d/, /\d/, '-', /\d/, /\d/, /\d/];
export function xor(a, b) {
  let len = Math.min(a.length, b.length);
  let arr = new Array(len);
  for (let i = 0; i < len; ++i)
    arr[i] = a.charCodeAt(i) ^ b.charCodeAt(i);
  return arr.join('');
}
