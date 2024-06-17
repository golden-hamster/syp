import PlaylistItem from '@/entity/article/PlaylistItem'

export default class Article {
  public id = 0
  public title = ''
  public content = ''
  public createdBy = ''
  public createdAt = ''
  public playlistItem: PlaylistItem[] = []

  constructor(data?: Partial<Article>) {
    if (data) {
      Object.assign(this, data)
      if ((data as any).playlistItemDtoList) {
        this.playlistItem = (data as any).playlistItemDtoList.map(
          (item: any) => new PlaylistItem(item)
        )
      }
    }
  }
}
