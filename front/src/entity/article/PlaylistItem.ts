export default class PlaylistItem {
  public videoId = ''
  public videoTitle = ''
  public thumbnailUrl = ''

  constructor(data?: Partial<PlaylistItem>) {
    if (data) {
      Object.assign(this, data)
    }
  }
}
