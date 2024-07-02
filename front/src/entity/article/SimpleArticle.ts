export default class SimpleArticle {
  public id = 0
  public title = ''
  public thumbnailUrl = ''
  public createdAt = ''
  public createdBy = ''
  public commentCount = 0

  constructor(data?: Partial<SimpleArticle>) {
    if (data) {
      Object.assign(this, data)
    }
  }
}
