export default class PlaylistItem {
  public videoId = ''
  public channelTitle = ''
  public thumbnailUrl = ''

  constructor(data?: Partial<PlaylistItem>) {
    if (data) {
      Object.assign(this, data)
    }
  }
}
