# No shebang. This file is loaded by other files.

reinstall_node_modules () {
    rm -rf node_modules
    npm install
}

clean_partly_dev () {
    rm -rf .shadow-cljs target
    rm -rf resources/public/compiler-output
}

clean_fully_dev () {
    clean_partly_dev
    reinstall_node_modules
}
