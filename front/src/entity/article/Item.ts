export default class Item {
  public videoId = ''
  public videoTitle = ''
  public thumbnailUrl = ''

  constructor(data?: Partial<Item>) {
    if (data) {
      Object.assign(this, data)
    }
  }
}
