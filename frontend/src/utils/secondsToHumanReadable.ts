export const secondsToHumanReadable = (totalSeconds: number) => { // TODO
  const minutes = Math.floor(totalSeconds / 60)
  const seconds = Math.floor(totalSeconds % 60)
  return (minutes < 10 ? '0' + minutes.toString(10) : minutes.toString(10)) +
    ':' + (seconds < 10 ? '0' + seconds.toString(10) : seconds.toString(10))
}
