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
    page: '/evolve/markov',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -130]
  },
  {
    character: 'Ida Lennox',
    page: '/evolve/lennox',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -250]
  },
  {
    character: 'Hyde',
    page: '/evolve/hyde',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -250]
  },
  {
    character: 'James Parnell',
    page: '/evolve/parnell',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -250]
  },
  {
    character: 'Torvald Stavig',
    page: '/evolve/torvald',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -370]
  }
]

export const evolveMenuMedicsData: ReadonlyArray<EvolveMenuData> = [
  {
    character: 'Alex "Slim"',
    page: '/evolve/slim',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -10]
  },
  {
    character: 'Caira Diaz',
    page: '/evolve/caira',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -370]
  },
  {
    character: 'Ðorde “Lazarus” Živkovic',
    page: '/evolve/lazarus',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -250]
  },
  {
    character: 'E.M.E.T.',
    page: '/evolve/emet',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -10]
  },
  {
    character: 'Valerie "Val" Wolski',
    page: '/evolve/val',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-490, -130]
  }
]

export const evolveMenuTrappersData: ReadonlyArray<EvolveMenuData> = [
  {
    character: 'Abraham "Abe" Presley',
    page: '/evolve/abe',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -10]
  },
  {
    character: 'Jack Arthur Lennox',
    page: '/evolve/scrap',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -370]
  },
  {
    character: 'Griffin Hallsey',
    page: '/evolve/griffin',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-250, -130]
  },
  {
    character: 'Khovalyg "Crow"',
    page: '/evolve/crow',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -130]
  },
  {
    character: 'Margaret "Maggie" Lumumba',
    page: '/evolve/maggie',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -10]
  }
]

export const evolveMenuSupportsData: ReadonlyArray<EvolveMenuData> = [
  {
    character: 'Bucket',
    page: '/evolve/bucket',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-130, -10]
  },
  {
    character: 'Henry “Hank” Allen',
    page: '/evolve/hank',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -250]
  },
  {
    character: 'Kala Kapur',
    page: '/evolve/tech',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-370, -370]
  },
  {
    character: 'Sunny Yú',
    page: '/evolve/sunny',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -370]
  },
  {
    character: 'William Cabot',
    page: '/evolve/cabot',
    imageSrc: spriteSrc,
    coordinatesOnSprite: [-10, -130]
  }
]
