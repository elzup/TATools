# ディレクトリ構造を統一
# 全てのファイルを第一階層に
for dir in $(\find . ! -path . -name "*fi*" -maxdepth 1 -type d); do
    \find "${dir}" ! -path "${dir}" -type d -print0 | while IFS= read -r -d '' subdir; do
        # echo $subdir
        \find "${subdir}" ! -path "${subdir}" -type f -print0 | while IFS= read -r -d '' filename; do
            echo "> ${filename} -> ${dir}"
            mv "${filename}" $dir
        done
    done
done

# 命名規則にそったファルダ名 (x先頭が数字) 99fi999 -> s99fi999
# src ディレクトリに移動

if [ ! -e ./src ]; then
    \mkdir src
fi
for dir in $(\find . ! -path . -name "1*fi*" -maxdepth 1 -type d |\sed "s|^\./||"); do
    mv $dir "src/s${dir}"
done

# 全ファイルを1プロジェクトで読み込むためにパッケージ名の行書き換える
for dir in $(\find src ! -path src -name "s1*fi*" -maxdepth 2 -type d |\sed "s|^\./||"); do
    for filename in $(\find "${dir}" ! -path "${dir}" -type f -name "*.java"); do
        packagename=`basename $dir`
        # BOM 削除
        nkf --overwrite -oc=UTF-8-BOM $filename
        \grep -l 'package' $filename |LC_ALL=C \xargs sed -i.bak -e "s/package .*;/package ${packagename};/g"
    done
done

