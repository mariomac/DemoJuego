@echo off
if exist fase1.dat del fase1.dat
DAT -a -t BMP -c1 -bpp 16 -s1 fase1.dat gfx\Tiles1.bmp
DAT -a -t BMP -c1 -bpp 16 -s1 fase1.dat gfx\fase1.bmp
ren fase1.dat fase1.dat