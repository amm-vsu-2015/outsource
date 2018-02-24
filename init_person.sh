

mkdir "$1"
mkdir "$1/1st_course"
mkdir "$1/2nd_course"
mkdir "$1/3rd_course"
mkdir "$1/4th_course"
mkdir "$1/5th_course"

make_keep() {
  echo "this file needs for keeping empty folders" > $1/.gitkeep
}

for path in $(find $1);
do
  if ! [ "$(ls -A $path)" ]; then
    make_keep $path
  fi
done
