# Dithering Service

Using just two colors - black and white - the service allows to imitate all original colors preserving the image quality. This technology is used in printers and monitors to render images using the limited set of colors (black and white set is just one of the possible usages).

## Examples
1. ### Original image
   ![](assets/original.jpg)

2. ### Black and white image without dithering
   Image was created by replacing darker colors with black, and lighter with white.
   ![](assets/not-dithered.jpg)

3. ### Black and white image using dithering
   ![](assets/dithered.jpg)

Let's compare the result:

<table>
	<tr>
    	<td> <img src="assets/not-dithered.jpg"  alt="Not dithered picture"></td>
    	<td> <img src="assets/dithered.jpg"  alt="Dithered picture"></td>
	</tr> 
</table>

Image quality is preserved using error diffusion. One of the most popular algorithms based on error diffusion is Floyd–Steinberg algorithm.

## Implementation
Let's iterate through image pixels from top to bottom and from left to right. We calculate the error using the following formula:

Let Х be the current pixel, p_i - adjacent pixel. Here's the submatrix containing adjacent to X pixels:

|   |   |   |
|---|---|---|
|    | X  | p4 |
| p1 | p2 | p3 |

Let's calculate an error for pixel X.
```
X' = originalColor - suggestedColor
```
Here `suggestedColor` is the closest color to the original one (black or white in our case). Then, we add X' value multiplied by a coefficient to the adjacent pixels.

Coefficient matrix:
|   |   |   |
|---|---|---|
|   |   |  7/16 |
| 3/16 | 5/16 | 1/16 |

New values:
|   |   |   |
|---|---|---|
|   | suggestedColor  |  p4 + X' * 7/16 |
| p1 + X' * 3/16 | p2 + X' * 5/16 | p3 + X' * 1/16 |


## Technologies used
- Java
- Spring Boot
- Gradle