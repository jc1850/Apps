for dir in *     # list directories in the form "/tmp/dirname/"
do
    dir=${dir%*/}      # remove the trailing "/"
    echo "${dir##*/}" >> .git/info/sparse-checkout     # print everything after the final "/"
done
