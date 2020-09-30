npm run build

pushd build/static/js

rm -- *.map -- *.txt
mv -f 2*.js vendor.js
mv -f main*.js main.js
mv -f runtime*.js runtime.js

popd

pushd build/static/css

rm -- *.map
mv -f 2*.css vendor.css
mv -f main*.css main.css

cd ..
tree

popd

echo "  ~~~ ENVIRONMENT ~~~   "
echo

cat .env

echo
echo "   ~~~ ENVIRONMENT ~~~   "
