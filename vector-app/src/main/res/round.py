import os
from PIL import Image, ImageDraw, ImageFilter

def make_circle(image_path, output_path, size):
    img = Image.open(image_path).convert("RGBA")
    img = img.resize((size, size), Image.LANCZOS)

    mask = Image.new('L', (size, size), 0)
    draw = ImageDraw.Draw(mask)
    draw.ellipse((0, 0, size, size), fill=255)
    mask = mask.filter(ImageFilter.GaussianBlur(1))

    img.putalpha(mask)

    img.save(output_path, "PNG")

sizes = {
    "mdpi": 48,
    "hdpi": 72,
    "xhdpi": 96,
    "xxhdpi": 144,
    "xxxhdpi": 192
}

for density, size in sizes.items():
    input_path = f"mipmap-{density}/ic_launcher.png"
    output_path = f"mipmap-{density}/ic_launcher_round.png"
    if os.path.exists(input_path):
        make_circle(input_path, output_path, size)
        print(f"Processed {output_path}")
    else:
        print(f"File {input_path} does not exist")

