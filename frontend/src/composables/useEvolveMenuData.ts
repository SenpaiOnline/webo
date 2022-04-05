export interface EvolveMenuData {
  character: string,
  imageSrc: string,
  page: string
  coordinatesOnSprite?: [number, number],
  width?: number,
  height?: number
}

const spriteSrc = '/evolve/evolve_characters.png'

export const evolveMenuAssaultsData: ReadonlyArray<EvolveMenuData> = [
  {
    character: 'Aleksey Markov',
    page: 'markov',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -130]
  },
  {
    character: 'Ida Lennox',
    page: 'lennox',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -250]
  },
  {
    character: 'Hyde',
    page: 'hyde',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -250]
  },
  {
    character: 'James Parnell',
    page: 'parnell',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -250]
  },
  {
    character: 'Torvald Stavig',
    page: 'torvald',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -370]
  }
]

export const evolveMenuMedicsData: ReadonlyArray<EvolveMenuData> = [
  {
    character: 'Alex "Slim"',
    page: 'slim',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -10]
  },
  {
    character: 'Caira Diaz',
    page: 'caira',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -370]
  },
  {
    character: 'Ðorde “Lazarus” Živkovic',
    page: 'lazarus',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -250]
  },
  {
    character: 'E.M.E.T.',
    page: 'emet',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -10]
  },
  {
    character: 'Valerie "Val" Wolski',
    page: 'val',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -130]
  }
]

export const evolveMenuTrappersData: ReadonlyArray<EvolveMenuData> = [
  {
    character: 'Abraham "Abe" Presley',
    page: 'abe',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -10]
  },
  {
    character: 'Jack Arthur Lennox',
    page: 'scrap',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -370]
  },
  {
    character: 'Griffin Hallsey',
    page: 'griffin',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -130]
  },
  {
    character: 'Khovalyg "Crow"',
    page: 'crow',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -130]
  },
  {
    character: 'Margaret "Maggie" Lumumba',
    page: 'maggie',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -10]
  }
]

export const evolveMenuSupportsData: ReadonlyArray<EvolveMenuData> = [
  {
    character: 'Bucket',
    page: 'bucket',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -10]
  },
  {
    character: 'Henry “Hank” Allen',
    page: 'hank',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -250]
  },
  {
    character: 'Kala Kapur',
    page: 'tech',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -370]
  },
  {
    character: 'Sunny Yú',
    page: 'sunny',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -370]
  },
  {
    character: 'William Cabot',
    page: 'cabot',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -130]
  }
]
