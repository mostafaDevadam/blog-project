export type USER = {
  _id?: string
  email?: string
  blogs?: BLOG[] | []
  created_at?: string
  updated_at?: string
}

export type USER_INPUT = {
  email: string
  password: string
}
//--------------
export type BLOG = {
  _id?: string
  title?: string
  content?: string
  owner?: USER | string
}

export type BLOG_INPUT = {
  title: string
  content?: string
  owner: string
}
//--------------
export type SHARE = {
  _id?: string
  owner?: USER | string
  friend?: USER | string
  blogs?: BLOG[] | []
}

export type SHARE_INPUT = {
  owner?: string | any
  //friend?: string
  remove_share_blogs?: string[]
  add_share_blogs?: string[]
  blogs?: string[]
}
//--------------
