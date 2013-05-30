#ifndef _fase1fondoGfx_c
#define _fase1fondoGfx_c


#ifndef _GFX_BITMAP_DATA
#define _GFX_BITMAP_DATA
/* structure with bitmap data */
typedef struct GFX_BITMAP_DATA
{
  int BitmapIdentifier;
  int BitmapWidth;
  int BitmapHeight;
  int TileWidth;
  int TileHeight;
  int TileCountX;
  int TileCountY;
  int TransparentX;
  int TransparentY;
  int Sequences;
  int ** SequenceData;
  int * SequenceLength;
  int * SequencePosition;
  int * SequenceCounter;

} GFX_BITMAP_DATA;
#endif


#ifndef _GFX_EMPTY
#define _GFX_EMPTY
static int Empty[] = {0};
#endif


/* tile sequences */

int Tiles1SeqLength[] =
{
  -1
};

int Tiles1SeqPosition[0 + 1];
int Tiles1SeqCounter[0 + 1];

int * Tiles1Sequences[] = 
{
  Empty
};



/* tile sequences */

int fase1fondoSeqLength[] =
{
  -1
};

int fase1fondoSeqPosition[0 + 1];
int fase1fondoSeqCounter[0 + 1];

int * fase1fondoSequences[] = 
{
  Empty
};



#define GfxTiles2Map1MapWidth (75)
#define GfxTiles2Map1MapHeight (10)

signed short Tiles2Map1MapData[10][75] =
  {{ 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 4, 5, 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1,
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 1, 1,
    1},
  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
  { 1, 6, 7, 8, 1, 9,10,11,12, 1, 1, 6, 7, 8, 1, 1, 6, 7, 8, 1, 1, 6, 7, 8, 1,
    1, 4, 5, 9,10,11,12, 1, 1, 6, 7, 8, 1, 1, 1, 1, 1, 6, 7, 8, 1, 1, 6, 7, 8,
    1, 4, 5,10,11,12, 1, 1, 6, 7, 8, 4, 5, 6, 7, 8, 1, 1, 6, 7, 8, 1, 9,10,11},
  { 9,13,14,15,16, 9,13,14,15,16, 9,13,14,15,16, 9,13,14,15,16, 9,13,14,15,16,
    1, 1, 1, 9,13,14,15,16, 9,13,14,15,16, 1, 1, 1,17,18,14,15,16, 9,13,14,15,
   16, 1, 9,13,14,15,16,17,18,14,15,16, 9,13,14,15,16,17,18,14,15,16, 9,13,14},
  {19,20,21,22,23,19,24,25,26,23,19,20,21,22,23,19,20,21,22,23,19,20,21,22,23,
    1, 1, 1,19,24,25,26,23,19,20,21,22,23,27,28, 1,29,30,21,22,23,19,20,21,22,
   23, 1,19,24,25,26,23,29,30,21,22,23,19,20,21,22,23,29,30,21,22,23,19,24,25},
  {31,32,33,34,35,31,32,33,34,35,31,32,33,34,36,37,32,33,34,36,37,32,33,34,35,
    1, 1, 1,31,32,33,34,36,37,32,33,34,35,38,39, 1,31,32,33,34,40,37,32,33,34,
   35, 1,31,32,33,34,40,37,32,33,34,36,37,32,33,34,36,37,32,33,34,35,31,32,33},
  {41,42,43,44,45,41,42,43,44,45,41,42,43,46,47,48,49,43,46,47,48,49,43,44,45,
    1, 1, 1,41,42,43,46,47,48,49,43,44,45, 1, 1, 1,41,42,43,46,50,48,49,43,44,
   45, 1,41,42,43,46,50,48,49,43,46,47,48,49,43,46,47,48,49,43,44,45,41,42,43},
  {51,52,53,54,55,51,52,53,54,55,51,52,53,56,57,58,59,53,56,57,58,59,53,54,55,
    1, 1, 1,51,52,53,56,57,58,59,53,54,55, 1, 4, 5,51,52,53,56,57,58,59,53,54,
   55, 1,51,52,53,56,57,58,59,53,56,57,58,59,53,56,57,58,59,53,54,55,51,52,53},
  {60,61,62,63,64,60,61,62,63,64,60,61,62,65,66,67,68,62,65,66,67,68,62,63,64,
    1, 1, 1,60,61,62,65,66,67,68,62,63,64, 1, 1, 1,60,61,62,65,66,67,68,62,63,
   64, 1,60,61,62,65,66,67,68,62,65,66,67,68,62,65,66,67,68,62,63,64,60,61,62},
  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

unsigned short Tiles2Map1BoundMapData[10][75] =
  {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0}};


/* tile sequences */

int Tiles2SeqLength[] =
{
  -1
};

int Tiles2SeqPosition[0 + 1];
int Tiles2SeqCounter[0 + 1];

int * Tiles2Sequences[] = 
{
  Empty
};

 

/* bitmap data */
const struct GFX_BITMAP_DATA GfxTiles1Bitmap = 
  { 3 - 0 - 1,  288, 64,  32, 32,
    18, 4,  224, 48, 0,
    &Tiles1Sequences[0], &Tiles1SeqLength[0],
    &Tiles1SeqPosition[0], &Tiles1SeqCounter[0]
  };

const struct GFX_BITMAP_DATA Gfxfase1fondoBitmap = 
  { 3 - 1 - 1,  288, 64,  32, 32,
    18, 4,  224, 48, 0,
    &fase1fondoSequences[0], &fase1fondoSeqLength[0],
    &fase1fondoSeqPosition[0], &fase1fondoSeqCounter[0]
  };

const struct GFX_BITMAP_DATA GfxTiles2Bitmap = 
  { 3 - 2 - 1,  288, 64,  16, 16,
    18, 4,  224, 48, 0,
    &Tiles2Sequences[0], &Tiles2SeqLength[0],
    &Tiles2SeqPosition[0], &Tiles2SeqCounter[0]
  };


#endif