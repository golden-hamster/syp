import Item from '@/entity/article/Item'
import { DateTimeFormatter } from '@js-joda/core'

export default class Article {
  public id = 0
  public title = ''
  public content = ''
  public createdBy = ''
  public createdAt = ''
  public commentCount = 0
  public item: Item[] = []

  constructor(data?: Partial<Article>) {
    if (data) {
      Object.assign(this, data)
      if ((data as any).itemDtoList) {
        this.item = (data as any).itemDtoList.map((item: any) => new Item(item))
      }
    }
  }
}
