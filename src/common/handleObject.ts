export const cleanObject = (data: any) => {
  let params = data

  for (let prop in params) {
    if (!params[prop]) {
      delete params[prop]
    }
  }
  return params
}
