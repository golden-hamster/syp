import Item from '@/entity/article/Item'

export default class Playlist {
  public id = 0
  public title = ''
  public createdAt = ''
  public createdBy = ''
  public items: Item[] = []

  constructor(data?: Partial<Playlist>) {
    if (data) {
      Object.assign(this, data)
      if ((data as any).items) {
        this.items = (data as any).items.map((item: any) => new Item(item))
      }
    }
  }
}
